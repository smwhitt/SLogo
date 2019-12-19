package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class NewlineToken extends Token{

    public NewlineToken(){
        tokenType = TokenType.NewlineToken;
        tokenName = "Newline";
    }

}
