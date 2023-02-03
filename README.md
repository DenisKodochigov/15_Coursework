# 15_Coursework
Distribution center is a part of the logistics system, warehouse and distribution complex. Trucks filled with different types of goods from suppliers arrive at the ports of unloading. Trucks are unloaded, goods from trucks are moved to the warehouse. The goods are removed from the warehouse and loaded into trucks located at the loading ports, which are then sent with delivery to the points of sale. The distribution center is characterized by the number of unloading ports and loading ports.

Port of unloading — the port of the distribution center, where the trucks that have come for unloading arrive and in which the truck is unloaded.

Loading port is the port of the distribution center where trucks arrive to load goods and further deliver goods to points of sale.

A truck is a truck that delivers goods to a distribution center and dispatches goods from a distribution center. It is characterized by a load capacity.

A commodity is a material object. The main characteristics are the weight and time required to load this product into and out of the truck.

Truck unloading is a process in which goods from a truck are moved to the warehouse of a distribution center. Unloading takes place in accordance with the time of unloading of the goods.

Truck loading is a process in which goods from the warehouse of the distribution center are moved to trucks in accordance with the weight of the goods, the time of loading of the goods, the load capacity of the truck.

The unloading waiting queue is a queue of trucks that are waiting for the unloading port to be released.

A distribution center warehouse is a room where goods are stored.
Description of the distribution center operation
The distribution center is a building with a limited number of unloading ports and loading ports. Trucks filled with goods arrive at the ports of unloading. The goods available in the truck are moved in turn to the warehouse of the distribution center for the time specified in the characteristics of the goods. The truck is in the port of unloading until the last of the goods transported by this truck gets to the warehouse. 
An empty truck drives away from the distribution center, it is no longer counted. The next truck pulls up to the vacant unloading port.

Trucks drive up to the loading ports to load the goods. The truck is being loaded: 
● If there is enough of a certain product in the warehouse for the maximum load of the truck, this quantity is removed from the warehouse.
● If there is not enough of a certain product in the warehouse for the maximum load of the truck, then all available units of this product are extracted.
● The extracted goods are moved to the truck in turn. Each unit of goods is loaded into the truck for the time specified in the characteristics of the goods.
● If the truck is loaded to the maximum, it moves away from the distribution center and is no longer counted. The next truck pulls up to the vacant loading port.
● The truck stays at the loading port until it is loaded with the maximum possible quantity of goods, taking into account the truck's load capacity.
● If there is space left in the truck, but there are no goods of the right type in the warehouse, the truck waits for the goods to appear in the warehouse and occupies the loading port. As soon as the necessary goods appear in the warehouse, they are removed from the warehouse and loaded into the truck according to the rules described above.
Trucks leaving the distribution center can only carry one type of goods.

The following processes take place inside the distribution center: 
● unloading of the incoming truck,
● sorting of goods by types and categories,
● loading a truck with a certain type of product for subsequent shipment to the point of sale.
Program requirements
1. Create at least three types of trucks with different load capacities. All types of trucks can be unloaded at the sorting center, but only two types with the smallest load capacity can be loaded for shipment.
2. Create at least four types of goods: large-sized, medium-sized, small-sized, food. For each of these four types, create several specific product categories. For example, the type of "Food products" includes the categories: "Bread", "Milk", "Potatoes" (for convenience, we can assume that these goods are delivered not piece by piece, but in packages).
Food products should not be delivered together with other types of goods.
3. With a certain frequency (for example, once a minute), a random truck is generated arriving at the distribution center. It should be filled with random types of goods. The goods inside the truck are arranged in a certain order. The quantity of each type of product must be random. The total number of goods must not exceed the load capacity of the truck. It is acceptable that the weight of goods in trucks for unloading is less than the load capacity of the truck, including the truck may be empty.
If there is a free unloading port in the distribution center, the truck arrives for unloading at this port. If there are no free ports, the truck gets in the queue waiting for unloading. As soon as the unloading port is released, the first truck from the waiting queue arrives at this port.
(!) For the correct operation of the truck generator, refer to the section "Tips and Recommendations".
4. The distribution center must have at least one loading port. In this case, three trucks of any load capacity can be unloaded, and only one truck of suitable load capacity can be loaded.
4.1. The requirement of increased complexity, perform as desired:
Increase the number of download ports to increase the capacity of your distribution center. For example, there are three unloading ports and already five loading ports.
(!) If you encounter difficulties, refer to the "Tips and Tricks" section to find solutions.
5. Unloading of trucks should take place in parallel.
6. Loading of trucks, if there are more than one loading ports, should take place in parallel.
7. If the distribution center has a free loading port, a truck arrives there to load a certain type of goods (the type of goods is selected randomly, the type of truck is selected randomly from among the suitable types). It is not necessary to generate trucks for loading with some delay. We assume that the truck fleet for loading is large enough and there are always free trucks.
8. The truck is loaded until it is loaded with the maximum possible quantity of goods, taking into account the load capacity of the truck. If there are no goods of this type at the moment, the truck is waiting and occupies the loading port. As soon as the truck is loaded as much as possible, it moves away from the exit port and is no longer taken into account, and the next truck for loading takes its place.
As a result, you should get a program that runs independently for some time and outputs the necessary information to the console, for example: 
● characteristics of trucks coming to unload;
● goods that arrive at the warehouse;
● goods that are removed from the warehouse;
● the process of unloading and loading trucks
and any other information that will help visualize the operation of the program.
Determine the time of the program or the conditions for the completion of the program yourself.

Распределительный центр — часть логистической системы, складской и распределительный комплекс. К портам разгрузки поступают грузовики, наполненные разными типами товаров от поставщиков. Грузовики разгружаются, товары из грузовиков перемещаются на склад. Со склада товары извлекаются и загружаются в грузовики, находящиеся в портах загрузки, которые потом отправляются с доставкой до точек продаж. Распределительный центр характеризуется количеством портов разгрузки и портов загрузки.

Порт разгрузки — порт распределительного центра, куда поступают пришедшие на разгрузку грузовики и в котором осуществляется разгрузка грузовика.

Порт загрузки — порт распределительного центра, куда поступают грузовики для загрузки товаров и дальнейшей доставки товаров в точки продаж.

Грузовик — грузовой автомобиль, с помощью которого осуществляется доставка товаров в распределительный центр и отправка товаров из распределительного центра. Характеризуется грузоподъёмностью.

Товар — материальный объект. Основные характеристики — вес и время, необходимое для погрузки этого товара в грузовик и выгрузки из него.

Разгрузка грузовика — процесс, при котором товары из грузовика перемещаются на склад распределительного центра. Разгрузка происходит в соответствии со временем выгрузки товара.

Загрузка грузовика — процесс, при котором товары со склада распределительного центра перемещаются в грузовики в соответствии с весом товара, временем погрузки товара, грузоподъёмностью грузовика.

Очередь ожидания разгрузки — очередь из грузовиков, которые ожидают освобождения порта разгрузки.

Склад распределительного центра — помещение, в котором хранятся товары.
Описание работы распределительного центра
Распределительный центр представляет из себя здание с ограниченным количеством портов разгрузки и портов загрузки. К портам разгрузки подъезжают грузовики, наполненные товарами. Имеющиеся в грузовике товары по очереди перемещаются на склад распределительного центра за время, указанное в характеристиках товара. Грузовик находится в порту разгрузки до тех пор, пока последний из товаров, перевозимых этим грузовиком, не попадёт на склад. 
Пустой грузовик отъезжает от распределительного центра, он более не учитывается. К освободившемуся порту разгрузки подъезжает следующий грузовик.

К портам загрузки подъезжают грузовики для загрузки товара. Происходит загрузка грузовика: 
●	Если на складе достаточно определённого товара для максимальной загрузки грузовика, это количество извлекается со склада.
●	Если на складе недостаточно определённого товара для максимальной загрузки грузовика, то извлекаются все имеющиеся единицы этого товара.
●	Извлечённые товары перемещаются в грузовик по очереди. Каждая единица товара загружается в грузовик за время, указанное в характеристиках товара.
●	Если грузовик загружен максимально, он отъезжает от распределительного центра и более не учитывается. К освободившемуся порту загрузки подъезжает следующий грузовик.
●	Грузовик находится в порту загрузки до тех пор, пока не будет загружен максимально возможным количеством товара с учётом грузоподъёмности грузовика.
●	Если в грузовике осталось место, но товаров нужного типа на складе нет, грузовик ожидает появления товаров на складе и занимает порт загрузки. Как только необходимые товары появляются на складе, они извлекаются со склада и загружаются в грузовик по вышеописанным правилам.
Грузовики, выезжающие из распределительного центра, могут перевозить только один тип товара.

Внутри распределительного центра происходят следующие процессы: 
●	разгрузка пришедшего грузовика,
●	сортировка товаров по типам и категориям,
●	загрузка грузовика товаром определённого типа для последующей отправки в точку продаж.
Требования к программе
1.	Создайте минимум три типа грузовиков с разной грузоподъёмностью. Разгружаться в сортировочном центре могут все типы грузовиков, однако загружаться на отправку могут только два типа с самой маленькой грузоподъёмностью.
2.	Создайте минимум четыре типа товаров: крупногабаритные, среднегабаритные, малогабаритные, пищевые. Для каждого из этих четырёх типов создайте несколько конкретных категорий товаров. Например, тип «Пищевые товары» включает в себя категории: «Хлеб», «Молоко», «Картофель» (для удобства можно считать, что данные товары доставляются не штучно, а упаковками).
Пищевые товары не должны доставляться вместе с товарами других типов.
3.	С определённой периодичностью (например, один раз в минуту) генерируется случайный грузовик, прибывающий в распределительный центр. Он должен быть наполнен случайными типами товаров. Товары внутри грузовика расположены в определённом порядке. Количество товара каждого типа должно быть случайным. Общее количество товаров не должно превышать грузоподъёмность грузовика. Допустимо, чтобы вес товаров в грузовиках на разгрузку был меньше, чем грузоподъёмность грузовика, в том числе грузовик может быть пустым.
Если в распределительном центре есть свободный порт разгрузки — грузовик поступает на разгрузку в этот порт. Если свободных портов нет — грузовик встаёт в очередь ожидания разгрузки. Как только порт разгрузки освобождается, то к этому порту подъезжает первый грузовик из очереди ожидания.
(!) Для корректной работы генератора грузовиков обратитесь к разделу «Советы и рекомендации».
4.	Распределительный центр должен иметь как минимум один порт загрузки. В этом случае разгружаться могут три грузовика любой грузоподъёмности, а загружаться может только один грузовик подходящей грузоподъёмности.
4.1.	Требование повышенной сложности, выполните по желанию:
Увеличьте количество портов загрузки для увеличения пропускной способности вашего распределительного центра. Например, три порта разгрузки и уже пять портов загрузки.
(!) Если возникнут сложности, обратитесь к разделу «Советы и рекомендации» для поиска путей решения.
5.	Разгрузка грузовиков должна проходить параллельно.
6.	Загрузка грузовиков, если портов загрузки более одного, должна проходить параллельно.
7.	Если у распределительного центра есть свободный порт загрузки — туда подъезжает грузовик для загрузки определённого типа товаров (тип товара выбирается случайно, тип грузовика выбирается случайно из числа подходящих типов). Генерировать грузовики для загрузки с какой-то задержкой не нужно. Предполагаем, что парк грузовиков для загрузки достаточно большой и свободные грузовики есть всегда.
8.	Грузовик загружается до тех пор, пока не будет загружен максимально возможным количеством товара, учитывая грузоподъёмность грузовика. Если на данный момент товаров этого типа нет — грузовик находится в ожидании и занимает порт загрузки. Как только грузовик максимально загружен, он отъезжает от выходного порта и более не учитывается, а его место занимает следующий грузовик для загрузки.
В результате должна получиться программа, которая самостоятельно выполняется на протяжении какого-то времени и выводит в консоль необходимую информацию, например: 
●	характеристики приходящих на разгрузку грузовиков;
●	товары, которые поступают на склад;
●	товары, которые извлекаются со склада;
●	процесс разгрузки и загрузки грузовиков
и любую другую информацию, которая поможет визуализировать работу программы.
Время работы программы или условия завершения программы определите самостоятельно.

