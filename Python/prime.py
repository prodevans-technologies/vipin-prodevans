'''
Python Script to check whether entered number is prime or not 

'''
n=int(raw_input('Enter number : '))
print(n)

flag=False

for i in range(2,n):
    if n%i==0:
        flag=True
        break
    else:
        flag=False

if flag:
    print("Not Prime")
else:
    print("prime number")
