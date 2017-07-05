import os
for a in os.environ:
    print('Var: ', a, 'Value: ', os.getenv(a))
print("all done")
