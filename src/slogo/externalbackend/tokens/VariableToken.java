package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class VariableToken extends Token {

    public VariableToken(){
        tokenType = TokenType.VariableToken;
        tokenName = "Variable";
    }

}
