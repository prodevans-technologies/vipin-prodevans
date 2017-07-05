ch=0
state={}
while ch!=5:
    print "Operations"
    print "1. CREATE"
    print "2. READ"
    print "3. UPDATE"
    print "4. DELETE"
    print "5. Quite Program"

    ch=int(raw_input('Enter your choice : '))

    if ch==1:
        code=raw_input('Enter State Code : ')
        name=raw_input('Enter State Name : ')
        for c,v in state.items():
            if c==code:
                print "Code already present"
                break
        else:
            state[code]=name
        
    elif ch==2:
        code=raw_input('Enter State Code to Read : ')
        for c,v in state.items():
            if c==code:
                print v
                break
        else:
            print "Record Not Found"

    elif ch==3:
        code=raw_input('Enter State Code to Update : ')
        name=raw_input('Enter Correct State Name : ')
        warning=raw_input('Do you really want to update ? [Y/N]')
        if warning in 'Yy':
            state[code]=name    

    elif ch==4:
        code=raw_input('Enter State Code to Update : ')
        warning=raw_input('Do you really want to update ? [Y/N]')
        if warning in 'Yy':
            state.pop(code,'Code Not available')
    else:
        print "quite program"
