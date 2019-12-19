package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class ListEndToken extends Token{

    public ListEndToken(){
        tokenType = TokenType.ListEndToken;
        tokenName = "ListEnd";
    }

}
