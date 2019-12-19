package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class CommandToken extends Token {

    private String commandName;

    public CommandToken(){
        tokenType = TokenType.CommandToken;
        tokenName = "Constant";
    }

}
