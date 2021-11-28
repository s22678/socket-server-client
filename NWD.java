public class NWD {
 
  /* NWD - program wyznacający największy wspólny dzielnik dwóch liczb
   * podanych jako parametry funkcji - algorytm Euklidesa
   */
 
    /*
     * pierwsza wersja funkcji, w której zastosowano wielokrotne odejmowanie
     * dwóch liczb
     */
    public static int NWD_1(int pierwsza, int druga)
    {
        while (pierwsza != druga) // dopóki dwie liczby nie są sobie równe
        {
            if (pierwsza > druga)  // sprawdzamy, która z nich jest większa
            {
                pierwsza = pierwsza - druga; // odejmujemy mniejszą liczbę
            }                               // od większej
            else
            {
                druga = druga - pierwsza;
            }
        }
        return pierwsza; 
    }
 
    /*
     * druga wersja algorytmu Euklidesa, w której zastosowano rekurencję
     * oraz operację modulo dwóch lich
     */
    public static int NWD_2(int pierwsza, int druga)
    {
        if (druga == 0) 
        {
            return pierwsza;  
        }
        else // rekurencyjne wywołanie funkcji, gdzie kolejność parametrów 
        {   // została zamieniona, dodatkowo drugi parametr to operacja modulo
            return NWD_2(druga, pierwsza%druga);  // dwóch liczb.
        }
    }
 
 
    public static void main(String[] args) 
    {
       System.out.println(NWD_1(130,20)); // przykład zastosowania podejścia 1
       System.out.println(NWD_2(130,20));  // oraz 2      
    }
}
