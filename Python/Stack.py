ch=0
stack=[]
while ch!=4:
    print "Stack Operation"
    print "1. PUSH"
    print "2. POP"
    print "3. DISPLAY"
    print "4. QUITE"

    ch=int(raw_input('Enter your choice : '))

    if ch==1:
        n=int(raw_input('Enter number to PUSH : '))
        stack.append(n)
        
    elif ch==2:
        if len(stack)>0:
            stack.pop()
        else:
            print "Stack is Underflow"
    elif ch==3:
        print stack
    else:
        print "quite program"
