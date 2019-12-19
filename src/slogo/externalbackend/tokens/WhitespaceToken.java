package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class WhitespaceToken extends Token{

    public WhitespaceToken(){
        tokenType = TokenType.WhitespaceToken;
        tokenName = "Whitespace";
    }

}
