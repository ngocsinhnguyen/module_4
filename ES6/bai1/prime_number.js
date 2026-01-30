
const isPrime = (n) => {
    if (n < 2) return false;
    for (let i = 2; i <= Math.sqrt(n); i++) {
        if (n % i === 0) return false;
    }
    return true;
};


const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];

const primeNumbers = numbers.filter(isPrime);

console.log("Mảng ban đầu:", numbers);
console.log("Các số nguyên tố:", primeNumbers);
