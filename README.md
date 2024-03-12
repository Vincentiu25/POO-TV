Tarsoaga Vincentiu-Ionut
324Ca

***Proiect POO : POO TV - Partea 1***

**Implementare:**

* Dupa ce sunt citite datele din input, acestea sunt stocate in variabila input
* In pachetul input sunt stocate clasele care sunt folosite pentru citirea
inputului
* In pachetul pages, sunt stocate paginile programului si unele clase suplimentare,
ajuta la diferite actiuni din cadrul programului
* Lista de filme citite si userii cititi sunt retinuti intr-o baza de date, care
este declarata singleton, deoarece pot sa instantiez una singura
* Folosesc variabile statice pentru a retine intotdeauna :
  * Pagina pe care ma aflu
  * Userul curent
  * Numele filmuli pe care ma aflu
  * Daca e nevoie, dupa actiunea filter, lista de filme filtrate
* De fiecare data cand vreau sa adaug in output un film, o lista de filme
sau un user, fac deepcopy pentru a creea o noua entitate identica cu cea initiala
* Fiecare pagina este reprezentata de o clasa specifica, cu mai multe metode, 
in functie de actiunile pe care le poate face un user pe pagina respectiva.
* La baza tuturor pagionilor este o pagina abstracta Page, care este mostenita de 
toate celelate pagini.
* La inceputul programului, toate paginile sunt alocate, pentru a putea naviga
printre ele ulterior.
* De fiecare data este citita o noua comanda si este executata actiunea 
corespunzatoare, cat timp indeplineste conditiile de a se afla pe o pagina caree 
permite efectuarea actiunii.
* Cand un user nou da register, acesta este retinut in baza de date
* De fiecare data cand avem de efectuat o actiune ce implica filmele, sunt 
luate doar filmele care nu sunt interzise in tara userului

* De fiecare data cand se executa actiunea change page, fvariabila statica
currentPage isi schimba numele in functie de pagina care trebuie accesata
* Pentru actiunea de filtrare, intai se varifica ce parametrii avem (rating,
duration, actors, genre). Mai apoi, daca se doreste o sortare, este aplicat
un bubble sort in functie de durata si de rating, daca este nevoie. In cazul 
in care durata nu este un parametru si sortarea trebuie facuta doar dupa rating,
este efectuat un bubble sort doar dupa ratingul filmelor. Pentru filtarre dupa
actori si dupa gen, este creeat un arrayList nou, unde sunt adaugate filmele care
se incadreaza in filtre. Acest arrayList nou este salvat in variabila statia, 
pentru a putea fi accesat daca este nevoie in comenzile urmatoare
* In cadrul actiunilor rate, like si watch se verifica intai daca filmul a fost 
vizualizat, respectiv cumparat, apoi sunt aplicate actiunile, in final fiind
adaugat in listele de filme ale userului

**Feedback**

Mi-a facut placere sa rezolv tema, deoarece mi-a aratat o noua utilizare pentru
conceputul OOP. Ca dificultate, nu a fost o tema foarte grea, cea mai dificila
parte fiind, dupa parerea mea, structurarea programului in clase.
