%%
=head1.* {
        yytext += 7;
        printf("<h1>%s</h1>", yytext);
    }
.|\n ECHO;
%%
int main() {
    yylex();
}
