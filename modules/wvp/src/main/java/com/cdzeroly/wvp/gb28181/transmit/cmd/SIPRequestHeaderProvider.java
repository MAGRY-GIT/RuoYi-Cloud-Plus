package com.cdzeroly.wvp.gb28181.transmit.cmd;

import com.cdzeroly.wvp.conf.SipConfig;
import com.cdzeroly.wvp.gb28181.SipLayer;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.bean.SipTransactionInfo;
import com.cdzeroly.wvp.gb28181.utils.SipUtils;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import com.cdzeroly.wvp.utils.GitUtil;
import gov.nist.javax.sip.message.SIPRequest;
import gov.nist.javax.sip.message.SIPResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.PeerUnavailableException;
import javax.sip.SipException;
import javax.sip.SipFactory;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.header.*;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * 摄像头命令request创造器
 *
 * @author swwheihei
 */
@Component
@RequiredArgsConstructor
public class SIPRequestHeaderProvider {

    private final SipConfig sipConfig;

    private final SipLayer sipLayer;

    private final GitUtil gitUtil;

    private final IRedisCatchStorage redisCatchStorage;


    /**
     * 创建消息请求
     *
     * @param device
     * @param content
     * @param viaTag
     * @param fromTag
     * @param toTag
     * @param callIdHeader
     * @return
     * @throws ParseException
     * @throws InvalidArgumentException
     * @throws PeerUnavailableException
     */
    public Request createMessageRequest(Device device, String content, String viaTag, String fromTag, String toTag, CallIdHeader callIdHeader) throws ParseException, InvalidArgumentException, PeerUnavailableException {
        AddressFactory addressFactory = SipFactory.getInstance().createAddressFactory();
        HeaderFactory headerFactory = SipFactory.getInstance().createHeaderFactory();
        // sipUri
        SipURI requestUrl = addressFactory.createSipURI(device.getDeviceId(), device.getHostAddress());
        // via
        ArrayList<ViaHeader> viaHeaders = new ArrayList<>();
        ViaHeader viaHeader = headerFactory.createViaHeader(sipLayer.getLocalIp(device.getLocalIp()), sipConfig.getPort(), device.getTransport(), viaTag);
        viaHeader.setRPort();
        viaHeaders.add(viaHeader);
        // from
        SipURI fromSipUrl = addressFactory.createSipURI(sipConfig.getId(), sipConfig.getDomain());
        Address fromAddress = addressFactory.createAddress(fromSipUrl);
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, fromTag);
        // to
        SipURI toSipUrl = addressFactory.createSipURI(device.getDeviceId(), device.getHostAddress());
        Address toAddress = addressFactory.createAddress(toSipUrl);
        ToHeader toHeader = headerFactory.createToHeader(toAddress, toTag);

        // Forwards
        MaxForwardsHeader maxForwards = headerFactory.createMaxForwardsHeader(70);
        // ceq
        CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(redisCatchStorage.getCSEQ(), Request.MESSAGE);

        Request request = SipFactory.getInstance().createMessageFactory().createRequest(requestUrl, Request.MESSAGE, callIdHeader, cSeqHeader, fromHeader, toHeader, viaHeaders, maxForwards);

        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));

        ContentTypeHeader contentTypeHeader = headerFactory.createContentTypeHeader("Application", "MANSCDP+xml");
        request.setContent(content, contentTypeHeader);
        return request;
    }

    /**
     * 创建邀请请求
     *
     * @param device
     * @param channelId
     * @param content
     * @param viaTag
     * @param fromTag
     * @param toTag
     * @param ssrc
     * @param callIdHeader
     * @return
     * @throws ParseException
     * @throws InvalidArgumentException
     * @throws PeerUnavailableException
     */
    public Request createInviteRequest(Device device, String channelId, String content, String viaTag, String fromTag, String toTag, String ssrc, CallIdHeader callIdHeader) throws ParseException, InvalidArgumentException, PeerUnavailableException {
        HeaderFactory headerFactory = SipFactory.getInstance().createHeaderFactory();
        AddressFactory addressFactory = SipFactory.getInstance().createAddressFactory();
        // 请求行
        SipURI requestLine = addressFactory.createSipURI(channelId, device.getHostAddress());
        // via
        ArrayList<ViaHeader> viaHeaders = new ArrayList<>();
        ViaHeader viaHeader = headerFactory.createViaHeader(sipLayer.getLocalIp(device.getLocalIp()), sipConfig.getPort(), device.getTransport(), viaTag);
        viaHeader.setRPort();
        viaHeaders.add(viaHeader);

        // from
        SipURI fromSipUrl = addressFactory.createSipURI(sipConfig.getId(), sipConfig.getDomain());
        Address fromAddress = addressFactory.createAddress(fromSipUrl);
        // 必须要有标记，否则无法创建会话，无法回应ack
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, fromTag);
        // to
        SipURI toSipUrl = addressFactory.createSipURI(channelId, device.getHostAddress());
        Address toAddress = addressFactory.createAddress(toSipUrl);
        ToHeader toHeader = headerFactory.createToHeader(toAddress, null);

        // Forwards
        MaxForwardsHeader maxForwards = headerFactory.createMaxForwardsHeader(70);

        // ceq
        CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(redisCatchStorage.getCSEQ(), Request.INVITE);

        Request request = SipFactory.getInstance().createMessageFactory().createRequest(requestLine, Request.INVITE, callIdHeader, cSeqHeader, fromHeader, toHeader, viaHeaders, maxForwards);

        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));

        Address concatAddress = addressFactory.createAddress(addressFactory.createSipURI(sipConfig.getId(), sipLayer.getLocalIp(device.getLocalIp()) + ":" + sipConfig.getPort()));
        // Address concatAddress = SipFactory.getInstance().createAddressFactory().createAddress(SipFactory.getInstance().createAddressFactory().createSipURI(sipConfig.getId(), device.getHost().getIp()+":"+device.getHost().getPort()));
        request.addHeader(headerFactory.createContactHeader(concatAddress));
        // Subject
        SubjectHeader subjectHeader = headerFactory.createSubjectHeader(String.format("%s:%s,%s:%s", channelId, ssrc, sipConfig.getId(), 0));
        request.addHeader(subjectHeader);
        ContentTypeHeader contentTypeHeader = headerFactory.createContentTypeHeader("APPLICATION", "SDP");
        request.setContent(content, contentTypeHeader);
        return request;
    }

    /**
     * 创建播放邀请请求
     *
     * @param device
     * @param channelId
     * @param content
     * @param viaTag
     * @param fromTag
     * @param toTag
     * @param callIdHeader
     * @param ssrc
     * @return
     * @throws ParseException
     * @throws InvalidArgumentException
     * @throws PeerUnavailableException
     */
    public Request createPlaybackInviteRequest(Device device, String channelId, String content, String viaTag, String fromTag, String toTag, CallIdHeader callIdHeader, String ssrc) throws ParseException, InvalidArgumentException, PeerUnavailableException {
        // 请求行
        SipURI requestLine = SipFactory.getInstance().createAddressFactory().createSipURI(channelId, device.getHostAddress());
        // via
        ArrayList<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
        HeaderFactory headerFactory = SipFactory.getInstance().createHeaderFactory();
        ViaHeader viaHeader = headerFactory.createViaHeader(sipLayer.getLocalIp(device.getLocalIp()), sipConfig.getPort(), device.getTransport(), viaTag);
        viaHeader.setRPort();
        viaHeaders.add(viaHeader);
        // from
        SipURI fromSipUrl = SipFactory.getInstance().createAddressFactory().createSipURI(sipConfig.getId(), sipConfig.getDomain());
        Address fromAddress = SipFactory.getInstance().createAddressFactory().createAddress(fromSipUrl);
        // 必须要有标记，否则无法创建会话，无法回应ack
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, fromTag);
        // to
        SipURI toSipUrl = SipFactory.getInstance().createAddressFactory().createSipURI(channelId, device.getHostAddress());
        Address toAddress = SipFactory.getInstance().createAddressFactory().createAddress(toSipUrl);
        ToHeader toHeader = headerFactory.createToHeader(toAddress, null);

        // Forwards
        MaxForwardsHeader maxForwards = headerFactory.createMaxForwardsHeader(70);

        // ceq
        CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(redisCatchStorage.getCSEQ(), Request.INVITE);
        Request request = SipFactory.getInstance().createMessageFactory().createRequest(requestLine, Request.INVITE, callIdHeader, cSeqHeader, fromHeader, toHeader, viaHeaders, maxForwards);

        Address concatAddress = SipFactory.getInstance().createAddressFactory().createAddress(SipFactory.getInstance().createAddressFactory().createSipURI(sipConfig.getId(), sipLayer.getLocalIp(device.getLocalIp()) + ":" + sipConfig.getPort()));
        // Address concatAddress = SipFactory.getInstance().createAddressFactory().createAddress(SipFactory.getInstance().createAddressFactory().createSipURI(sipConfig.getId(), device.getHost().getIp()+":"+device.getHost().getPort()));
        request.addHeader(headerFactory.createContactHeader(concatAddress));

        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));

        // Subject
        SubjectHeader subjectHeader = headerFactory.createSubjectHeader(String.format("%s:%s,%s:%s", channelId, ssrc, sipConfig.getId(), 0));
        request.addHeader(subjectHeader);

        ContentTypeHeader contentTypeHeader = headerFactory.createContentTypeHeader("APPLICATION", "SDP");
        request.setContent(content, contentTypeHeader);
        return request;
    }


    /**
     * 创建字节请求
     *
     * @param device
     * @param channelId
     * @param transactionInfo
     * @return
     * @throws ParseException
     * @throws InvalidArgumentException
     * @throws PeerUnavailableException
     */
    public Request createByteRequest(Device device, String channelId, SipTransactionInfo transactionInfo) throws ParseException, InvalidArgumentException, PeerUnavailableException {
        Request request = null;
        HeaderFactory headerFactory = SipFactory.getInstance().createHeaderFactory();
        AddressFactory addressFactory = SipFactory.getInstance().createAddressFactory();
        // 请求行
        SipURI requestLine = addressFactory.createSipURI(channelId, device.getHostAddress());
        // via
        ArrayList<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
        ViaHeader viaHeader = headerFactory.createViaHeader(sipLayer.getLocalIp(device.getLocalIp()), sipConfig.getPort(), device.getTransport(), transactionInfo.getViaBranch());
        viaHeaders.add(viaHeader);
        // from
        SipURI fromSipUrl = addressFactory.createSipURI(sipConfig.getId(), sipLayer.getLocalIp(device.getLocalIp()) + ":" + sipConfig.getPort());
        Address fromAddress = addressFactory.createAddress(fromSipUrl);
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, transactionInfo.getFromTag());
        // to
        SipURI toSipUrl = addressFactory.createSipURI(channelId, device.getHostAddress());
        Address toAddress = addressFactory.createAddress(toSipUrl);
        ToHeader toHeader = headerFactory.createToHeader(toAddress, transactionInfo.getToTag());

        // Forwards
        MaxForwardsHeader maxForwards = headerFactory.createMaxForwardsHeader(70);

        // ceq
        return getRequest(device, transactionInfo, requestLine, viaHeaders, fromHeader, toHeader, maxForwards);
    }

    @NotNull
    private Request getRequest(Device device, SipTransactionInfo transactionInfo, SipURI requestLine, ArrayList<ViaHeader> viaHeaders, FromHeader fromHeader, ToHeader toHeader, MaxForwardsHeader maxForwards) throws ParseException, InvalidArgumentException, PeerUnavailableException {
        HeaderFactory headerFactory = SipFactory.getInstance().createHeaderFactory();
        CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(redisCatchStorage.getCSEQ(), Request.BYE);
        CallIdHeader callIdHeader = headerFactory.createCallIdHeader(transactionInfo.getCallId());
        Request request = SipFactory.getInstance().createMessageFactory().createRequest(requestLine, Request.BYE, callIdHeader, cSeqHeader, fromHeader, toHeader, viaHeaders, maxForwards);
        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));

        Address concatAddress = SipFactory.getInstance().createAddressFactory().createAddress(SipFactory.getInstance().createAddressFactory().createSipURI(sipConfig.getId(), sipLayer.getLocalIp(device.getLocalIp()) + ":" + sipConfig.getPort()));
        request.addHeader(headerFactory.createContactHeader(concatAddress));

        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));

        return request;
    }

    /**
     * 为设备邀请创建字节请求
     *
     * @param device
     * @param channelId
     * @param transactionInfo
     * @return
     * @throws ParseException
     * @throws InvalidArgumentException
     * @throws PeerUnavailableException
     */
    public Request createByteRequestForDeviceInvite(Device device, String channelId, SipTransactionInfo transactionInfo) throws ParseException, InvalidArgumentException, PeerUnavailableException {
        HeaderFactory headerFactory = SipFactory.getInstance().createHeaderFactory();
        AddressFactory addressFactory = SipFactory.getInstance().createAddressFactory();
        // 请求行
        SipURI requestLine = addressFactory.createSipURI(channelId, device.getHostAddress());
        // via
        ArrayList<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
        ViaHeader viaHeader = headerFactory.createViaHeader(sipLayer.getLocalIp(device.getLocalIp()), sipConfig.getPort(), device.getTransport(), SipUtils.getNewViaTag());
        viaHeaders.add(viaHeader);
        // from
        SipURI fromSipUrl = addressFactory.createSipURI(sipConfig.getId(), sipConfig.getDomain());
        Address fromAddress = addressFactory.createAddress(fromSipUrl);
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, transactionInfo.getToTag());
        // to
        SipURI toSipUrl = addressFactory.createSipURI(channelId, device.getHostAddress());
        Address toAddress = addressFactory.createAddress(toSipUrl);
        ToHeader toHeader = headerFactory.createToHeader(toAddress, transactionInfo.getFromTag());

        // Forwards
        MaxForwardsHeader maxForwards = headerFactory.createMaxForwardsHeader(70);

        // ceq
        return getRequest(device, transactionInfo, requestLine, viaHeaders, fromHeader, toHeader, maxForwards);
    }

    /**
     * 创建订阅请求
     *
     * @param device
     * @param content
     * @param requestOld
     * @param expires
     * @param event
     * @param callIdHeader
     * @return
     * @throws ParseException
     * @throws InvalidArgumentException
     * @throws PeerUnavailableException
     */
    public Request createSubscribeRequest(Device device, String content, SIPRequest requestOld, Integer expires, String event, CallIdHeader callIdHeader) throws ParseException, InvalidArgumentException, PeerUnavailableException {
        AddressFactory addressFactory = SipFactory.getInstance().createAddressFactory();
        HeaderFactory headerFactory = SipFactory.getInstance().createHeaderFactory();
        // sipUri
        SipURI requestUrl = addressFactory.createSipURI(device.getDeviceId(), device.getHostAddress());
        // via
        ArrayList<ViaHeader> viaHeaders = new ArrayList<>();
        ViaHeader viaHeader = headerFactory.createViaHeader(sipLayer.getLocalIp(device.getLocalIp()), sipConfig.getPort(), device.getTransport(), SipUtils.getNewViaTag());
        viaHeader.setRPort();
        viaHeaders.add(viaHeader);
        // from
        SipURI fromSipUrl = addressFactory.createSipURI(sipConfig.getId(), sipConfig.getDomain());
        Address fromAddress = addressFactory.createAddress(fromSipUrl);
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, requestOld == null ? SipUtils.getNewFromTag() : requestOld.getFromTag());
        // to
        SipURI toSipURI = addressFactory.createSipURI(device.getDeviceId(), device.getHostAddress());
        Address toAddress = addressFactory.createAddress(toSipURI);
        ToHeader toHeader = headerFactory.createToHeader(toAddress, requestOld == null ? null : requestOld.getToTag());

        // Forwards
        MaxForwardsHeader maxForwards = headerFactory.createMaxForwardsHeader(70);

        // ceq
        CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(redisCatchStorage.getCSEQ(), Request.SUBSCRIBE);

        Request request = SipFactory.getInstance().createMessageFactory().createRequest(requestUrl, Request.SUBSCRIBE, callIdHeader, cSeqHeader, fromHeader, toHeader, viaHeaders, maxForwards);


        Address concatAddress = addressFactory.createAddress(addressFactory.createSipURI(sipConfig.getId(), sipLayer.getLocalIp(device.getLocalIp()) + ":" + sipConfig.getPort()));
        request.addHeader(headerFactory.createContactHeader(concatAddress));

        // Expires
        ExpiresHeader expireHeader = headerFactory.createExpiresHeader(expires);
        request.addHeader(expireHeader);

        // Event
        EventHeader eventHeader = headerFactory.createEventHeader(event);

        int random = (int) Math.floor(Math.random() * 10000);
        eventHeader.setEventId(random + "");
        request.addHeader(eventHeader);

        ContentTypeHeader contentTypeHeader = headerFactory.createContentTypeHeader("Application", "MANSCDP+xml");
        request.setContent(content, contentTypeHeader);

        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));

        return request;
    }

    /**
     * 创建信息请求
     *
     * @param device
     * @param channelId
     * @param content
     * @param transactionInfo
     * @return
     * @throws SipException
     * @throws ParseException
     * @throws InvalidArgumentException
     */
    public SIPRequest createInfoRequest(Device device, String channelId, String content, SipTransactionInfo transactionInfo) throws SipException, ParseException, InvalidArgumentException {
        if (device == null || transactionInfo == null) {
            return null;
        }
        AddressFactory addressFactory = SipFactory.getInstance().createAddressFactory();
        HeaderFactory headerFactory = SipFactory.getInstance().createHeaderFactory();
        // 请求行
        SipURI requestLine = addressFactory.createSipURI(channelId, device.getHostAddress());
        // via
        ArrayList<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
        ViaHeader viaHeader = headerFactory.createViaHeader(sipLayer.getLocalIp(device.getLocalIp()), sipConfig.getPort(), device.getTransport(), SipUtils.getNewViaTag());
        viaHeaders.add(viaHeader);
        // from
        SipURI fromSipURI = addressFactory.createSipURI(sipConfig.getId(), sipConfig.getDomain());
        Address fromAddress = addressFactory.createAddress(fromSipURI);
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, transactionInfo.getFromTag());
        // to
        SipURI toSipURI = addressFactory.createSipURI(channelId, device.getHostAddress());
        Address toAddress = addressFactory.createAddress(toSipURI);
        ToHeader toHeader = headerFactory.createToHeader(toAddress, transactionInfo.getToTag());

        // Forwards
        MaxForwardsHeader maxForwards = headerFactory.createMaxForwardsHeader(70);

        // ceq
        CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(redisCatchStorage.getCSEQ(), Request.INFO);
        CallIdHeader callIdHeader = headerFactory.createCallIdHeader(transactionInfo.getCallId());
        SIPRequest request = (SIPRequest) SipFactory.getInstance().createMessageFactory().createRequest(requestLine, Request.INFO, callIdHeader, cSeqHeader, fromHeader, toHeader, viaHeaders, maxForwards);

        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));

        Address concatAddress = addressFactory.createAddress(addressFactory.createSipURI(sipConfig.getId(), sipLayer.getLocalIp(device.getLocalIp()) + ":" + sipConfig.getPort()));
        request.addHeader(headerFactory.createContactHeader(concatAddress));

        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));

        if (content != null) {
            ContentTypeHeader contentTypeHeader = headerFactory.createContentTypeHeader("Application", "MANSRTSP");
            request.setContent(content, contentTypeHeader);
        }
        return request;
    }

    /**
     * 创建确认请求
     *
     * @param localIp
     * @param sipUrl
     * @param sipResponse
     * @return
     * @throws ParseException
     * @throws InvalidArgumentException
     * @throws PeerUnavailableException
     */
    public Request createAckRequest(String localIp, SipURI sipUrl, SIPResponse sipResponse) throws ParseException, InvalidArgumentException, PeerUnavailableException {

        HeaderFactory headerFactory = SipFactory.getInstance().createHeaderFactory();
        MessageFactory messageFactory = SipFactory.getInstance().createMessageFactory();
        AddressFactory addressFactory = SipFactory.getInstance().createAddressFactory();
        // via
        ArrayList<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
        ViaHeader viaHeader = headerFactory.createViaHeader(localIp, sipConfig.getPort(), sipResponse.getTopmostViaHeader().getTransport(), SipUtils.getNewViaTag());
        viaHeaders.add(viaHeader);

        // Forwards
        MaxForwardsHeader maxForwards = headerFactory.createMaxForwardsHeader(70);

        // ceq
        CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(sipResponse.getCSeqHeader().getSeqNumber(), Request.ACK);

        Request request = messageFactory.createRequest(sipUrl, Request.ACK, sipResponse.getCallIdHeader(), cSeqHeader, sipResponse.getFromHeader(), sipResponse.getToHeader(), viaHeaders, maxForwards);

        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));


        Address concatAddress = addressFactory.createAddress(addressFactory.createSipURI(sipConfig.getId(), localIp + ":" + sipConfig.getPort()));
        request.addHeader(headerFactory.createContactHeader(concatAddress));
        request.addHeader(SipUtils.createUserAgentHeader(gitUtil));

        return request;
    }
}
