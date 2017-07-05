'''
Python Script to generate prime numbers upto n
'''
n=int(raw_input('Enter your limit : '))

flag=True

for i in range(2,n):
    for x in range(2,i):
        if i%x==0:
            flag=True
            break
        else:
            if x==i-1:
                flag=False
        if not flag:
            print(i)
            
        
            
        
