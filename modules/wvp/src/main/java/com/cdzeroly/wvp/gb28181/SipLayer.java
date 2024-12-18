package com.cdzeroly.wvp.gb28181;

import com.cdzeroly.wvp.common.NetProtocol;
import com.cdzeroly.wvp.conf.SipConfig;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.gb28181.domian.parser.GbStringMsgParserFactory;
import com.cdzeroly.wvp.gb28181.conf.DefaultProperties;
import com.cdzeroly.wvp.gb28181.transmit.ISIPProcessorObserver;
import gov.nist.javax.sip.SipProviderImpl;
import gov.nist.javax.sip.SipStackImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.sip.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MGARY
 */
@Slf4j
@Component
@Order(value=10)
@AllArgsConstructor
public class SipLayer implements CommandLineRunner {

	private final SipConfig sipConfig;

	private final ISIPProcessorObserver sipProcessorObserver;

	private final UserSetting userSetting;

	/**
	 * TCP SIP 提供商
	 */
	private final Map<String, SipProviderImpl> tcpSipProviderMap = new ConcurrentHashMap<>();
	/**
	 * UDP SIP 提供商
	 */
	private final Map<String, SipProviderImpl> udpSipProviderMap = new ConcurrentHashMap<>();
	/**
	 * 监听IP
	 */
	private final List<String> monitorIps = new ArrayList<>();

	@Override
	public void run(String... args) {
		if (ObjectUtils.isEmpty(sipConfig.getIp())) {
			try {
				// 获得本机的所有网络接口
				Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
				while (nifs.hasMoreElements()) {
					NetworkInterface nif = nifs.nextElement();
					// 获得与该网络接口绑定的 IP 地址，一般只有一个
					Enumeration<InetAddress> addresses = nif.getInetAddresses();
					while (addresses.hasMoreElements()) {
						InetAddress addr = addresses.nextElement();
						if (addr instanceof Inet4Address) {
							if ("127.0.0.1".equals(addr.getHostAddress())){
								continue;
							}
							if (nif.getName().startsWith("docker")) {
								continue;
							}
							log.info("[自动配置SIP监听网卡] 网卡接口地址： {}", addr.getHostAddress());
							// 只关心 IPv4 地址
							monitorIps.add(addr.getHostAddress());
						}
					}
				}
			}catch (Exception e) {
				log.error("[读取网卡信息失败]", e);
			}
			if (monitorIps.isEmpty()) {
				log.error("[自动配置SIP监听网卡信息失败]， 请手动配置SIP.IP后重新启动");
				System.exit(1);
			}
		}else {
			// 使用逗号分割多个ip
			String separator = ",";
			if (sipConfig.getIp().indexOf(separator) > 0) {
				String[] split = sipConfig.getIp().split(separator);
				monitorIps.addAll(Arrays.asList(split));
			}else {
				monitorIps.add(sipConfig.getIp());
			}
		}
		if (ObjectUtils.isEmpty(sipConfig.getShowIp())){
			sipConfig.setShowIp(String.join(",", monitorIps));
		}
        SipFactory.getInstance().setPathName("gov.nist");
		if (!monitorIps.isEmpty()) {
			for (String monitorIp : monitorIps) {
				addListeningPoint(monitorIp, sipConfig.getPort());
			}
			if (udpSipProviderMap.size() + tcpSipProviderMap.size() == 0) {
				System.exit(1);
			}
		}
	}

	/**
	 * 添加侦听点
	 * @param monitorIp  监听IP
	 * @param port  端口
	 */
	private void addListeningPoint(String monitorIp, int port){
		SipStackImpl sipStack;
		try {
			sipStack = (SipStackImpl)SipFactory.getInstance().createSipStack(DefaultProperties.getProperties("GB28181_SIP", userSetting.getSipLog()));
			//添加解析器
			sipStack.setMessageParserFactory(new GbStringMsgParserFactory());
		} catch (PeerUnavailableException e) {
			log.error("{}", monitorIp,e);
			return;
		}

		try {
			ListeningPoint tcpListeningPoint = sipStack.createListeningPoint(monitorIp, port, NetProtocol.TCP.name());
			SipProviderImpl tcpSipProvider = (SipProviderImpl)sipStack.createSipProvider(tcpListeningPoint);

			tcpSipProvider.setDialogErrorsAutomaticallyHandled();
			tcpSipProvider.addSipListener(sipProcessorObserver);
			tcpSipProviderMap.put(monitorIp, tcpSipProvider);
			log.info("[SIP SERVER] tcp://{}:{} 启动成功", monitorIp, port);
		} catch (TransportNotSupportedException
				 | TooManyListenersException
				 | ObjectInUseException
				 | InvalidArgumentException e) {
			log.error("[SIP SERVER] tcp://{}:{} SIP服务启动失败,请检查端口是否被占用或者ip是否正确"
					, monitorIp, port);
		}

		try {
			ListeningPoint udpListeningPoint = sipStack.createListeningPoint(monitorIp, port, NetProtocol.UDP.name());

			SipProviderImpl udpSipProvider = (SipProviderImpl)sipStack.createSipProvider(udpListeningPoint);
			udpSipProvider.addSipListener(sipProcessorObserver);
			udpSipProvider.setDialogErrorsAutomaticallyHandled();
			udpSipProviderMap.put(monitorIp, udpSipProvider);

			log.info("[SIP SERVER] udp://{}:{} 启动成功", monitorIp, port);
		} catch (TransportNotSupportedException
				 | TooManyListenersException
				 | ObjectInUseException
				 | InvalidArgumentException e) {
			log.error("[SIP SERVER] udp://{}:{} SIP服务启动失败,请检查端口是否被占用或者ip是否正确"
					, monitorIp, port);
		}
	}

	/**
	 * 获取 UDP SIP 提供程序
	 * @param ip IP地址
	 * @return  SipProviderImpl
	 */
	public SipProviderImpl getUdpSipProvider(String ip) {
		if (udpSipProviderMap.size() == 1) {
			return udpSipProviderMap.values().stream().findFirst().get();
		}
		if (ObjectUtils.isEmpty(ip)) {
			return null;
		}
		return udpSipProviderMap.get(ip);
	}

	/**
	 * 获取首个UDP通道
	 * @return  SipProviderImpl
	 */
	public SipProviderImpl getUdpSipProvider() {
		if (udpSipProviderMap.size() != 1) {
			return null;
		}
		return udpSipProviderMap.values().stream().findFirst().get();
	}

	/**
	 * 获取首个TCP通道
	 * @return  SipProviderImpl
	 */
	public SipProviderImpl getTcpSipProvider() {
		if (tcpSipProviderMap.size() != 1) {
			return null;
		}
		return tcpSipProviderMap.values().stream().findFirst().get();
	}
	/**
	 * 根据IP获取首个TCP通道
	 * @return  SipProviderImpl
	 */
	public SipProviderImpl getTcpSipProvider(String ip) {
		if (tcpSipProviderMap.size() == 1) {
			return tcpSipProviderMap.values().stream().findFirst().get();
		}
		if (ObjectUtils.isEmpty(ip)) {
			return null;
		}
		return tcpSipProviderMap.get(ip);
	}

	public String getLocalIp(String deviceLocalIp) {
		if (monitorIps.size() == 1) {
			return monitorIps.get(0);
		}
		if (!ObjectUtils.isEmpty(deviceLocalIp)) {
			return deviceLocalIp;
		}
		return getUdpSipProvider().getListeningPoint().getIPAddress();
	}
}
