%%
%class Lexer
%standalone

Head1 = "=head1" .*

%%
{Head1} {
    System.out.print("<h1>" + yytext().substring(6).trim() + "</h1>" );
}
