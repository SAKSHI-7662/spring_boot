#include <stdio.h>
#include <stdbool.h>
bool isEven(int number) {
    return number % 2 == 0;
}

int main() {
    int number;
    printf("Enter a number: ");
    scanf("%d", &number);
    bool result = isEven(number);

    if (result) {
        printf("true\n"); 
    } else {
        printf("false\n"); 
    }
}