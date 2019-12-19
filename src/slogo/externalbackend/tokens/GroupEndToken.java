package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class GroupEndToken extends Token{

    public GroupEndToken(){
        tokenType = TokenType.GroupEndToken;
        tokenName = "GroupEnd";
    }

}
