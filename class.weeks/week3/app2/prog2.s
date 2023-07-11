.data
.PRTSTR: .string "%d\n"
x: .quad 4
.text
.global main
main:
movl $4, %eax
movl %eax, x
movl $3, %ebx
movl x, %ecx
movl $2, %eax
imul %ecx, %eax
addl %ebx, %eax
movl %eax, %esi
leaq .PRTSTR(%rip), %rdi
movl $0, %eax
call printf@PLT

