package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class ConstantToken extends Token{

    public ConstantToken(){
        tokenType = TokenType.ConstantToken;
        tokenName = "Constant";
    }

}
