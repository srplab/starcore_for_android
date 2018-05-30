def tt(a,b) 
    puts(a,b)
    return 666,777
end    
$g1 = 123
def yy(a,b,z) 
    puts(a,b,z)
    return {'jack'=> 4098, 'sape'=> 4139}
end
class Multiply
    def initialize(x,y) 
        @a = x
        @b = y
    end
    
    def multiply(a,b)
        puts("multiply....",self,a,b)
        return a * b
    end
end    