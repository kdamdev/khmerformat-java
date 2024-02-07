# Build
`Follow this below guide to modify library`

Pre-requires
* Java 8+
* Maven as its build tool.

How to build
* `mvn clean install`

# How to install
````
<dependency>
  <groupId>io.github.kdamdev</groupId>
  <artifactId>khmerformat</artifactId>
  <version>1.0.1</version>
</dependency>
````
# Usages
##  Khmer Lunar Date
````
LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 4, 13 ));
lunar.toString()

// ថ្ងៃសៅរ៍ ៥ កើត ខែចេត្រ ឆ្នាំរោង បញ្ចស័ក ព.ស.២៥៦៧
````
````
LunarDate lunar = KhmerDate.LunarDate(LocalDate.now());
lunar.toString()
// current date
````
#### or

````
LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 4, 13 ));
lunar.getDayOfWeek() // សៅរ៍
lunar.getDayOfMonth() // ៥ កើត
lunar.getMonth() // ចេត្រ
lunar.getZodiacYear() // រោង
lunar.getEra() // បញ្ចស័ក
lunar.getBeYear() // ២៥៦៧
````
##  Khmer Solar Date
````
SolarDate solarDate = KhmerDate.SolarDate(LocalDate.of(2024, 4, 13 ));
solarDate.toString()

// ថ្ងៃទី១៣ ខែមេសា ឆ្នាំ២០២៤
````
#### or

````
SolarDate solarDate = KhmerDate.SolarDate(LocalDate.of(2024, 4, 13 ));
solarDate.getDay() // ១៣
solarDate.getMonth() // មេសា
solarDate.getYear() // ២០២៤
````
##  Khmer Numeric
````
Numeric numeric = KhmerNumeric.Numeric("123456789");
numeric.toKhmer() // ១២៣៤៥៦៧៨៩
numeric.toKhmer(true) // ១២៣,៤៥៦,៧៨៩
numeric.toKhmerText() // មួយរយម្ភៃបីលានបួនរយហាសិបប្រាំមួយពាន់ប្រាំពីររយប៉ែតសិបប្រាំបួន
````

# Reference
1. [Khmer New Year Time Calculation](http://www.dahlina.com/education/khmer_new_year_time.html)
2. Choun Nat Dictionary && Khmer Dictionary 2022  
3. [ThmeyThmey](https://thmeythmey.com/?page=detail&id=59282)
4. [wikipedia ចន្ទគតិ](https://km.m.wikipedia.org/wiki/%E1%9E%85%E1%9E%93%E1%9F%92%E1%9E%91%E1%9E%82%E1%9E%8F%E1%9E%B7)
5. [wikipedia](https://km.wikipedia.org/wiki/%E1%9E%A2%E1%9E%B6%E1%9E%9F%E1%9E%B6%E1%9E%8D)
6. [https://btkhmer.com/Cult-Mohasankran-KH.pdf](https://btkhmer.com/Cult-Mohasankran-KH.pdf)
