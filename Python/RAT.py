side1=int(raw_input('Enter value for side1 : '))
side2=int(raw_input('Enter value for side2 : '))
side3=int(raw_input('Enter value for side3 : '))

l=[side1,side2,side3]
maxx=max(l)
minn=min(l)

if math.pow(maxx,2) == ( (math.pow(minn,2)) + (math.pow(,2)) ):
    print ("Entered sides are of Right Angle Triangle")
else
    print ("Entered sides are of NOT a Right Angle Triangle")
