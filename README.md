# Sample Application use Advanced Encryption Standard AES to generate a file with, encrypted information.

The application generate in this case, the output.txt file, it contains encrypted information.


## The AES256 class file, has three important constants values: 
* **SECRET_KEY** - This secret key :key:, must be the same for both operations, to encrypt a decrypt files.
* **SALT** - This string help to generate a unique secret key, be aware that this value should be generated randomly, for this example a fixed values was used. I recommend investigate 
in OWASP Guidelines, to learn the properly way to generate a random SALT.
* **IV** - This algorithm require an initialization vector of 16 bytes, could by any integer number, with the minimum value of -128 and a maximum value of 127 (inclusive).

The file conn.txt contains info that need to be encrypted. 

## WARNING :warning:
In this example, this three values could be modified, but the same values SECRET_KEY, SALT and IV are necessary, for encryption and decryption operations.
