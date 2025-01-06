package com.cdzeroly.wvp.gb28181.parser;

import com.cdzeroly.wvp.domain.parser.GBStringMsgParser;
import gov.nist.javax.sip.parser.MessageParser;
import gov.nist.javax.sip.parser.MessageParserFactory;
import gov.nist.javax.sip.stack.SIPTransactionStack;

/**
 * GB 字符串 msg 解析器工厂
 * @author MGARY
 */
public class GbStringMsgParserFactory implements MessageParserFactory {

    /**
     * msg parser is completely stateless, reuse isntance for the whole stack
     * fixes https://github.com/RestComm/jain-sip/issues/92
     */
    private static final GBStringMsgParser MSG_PARSER = new GBStringMsgParser();
    /*
     * (non-Javadoc)
     * @see gov.nist.javax.sip.parser.MessageParserFactory#createMessageParser(gov.nist.javax.sip.stack.SIPTransactionStack)
     */
    @Override
    public MessageParser createMessageParser(SIPTransactionStack stack) {
        return MSG_PARSER;
    }
}
