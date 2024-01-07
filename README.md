# Build
`Follow this below guide to modify library`

Pre-requires
* Java 8+
* Maven as its build tool.

How to build
* `mvn clean install`

# Usages
##  Khmer Lunar Date
````
LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 4, 13 ));
lunar.toString()

// ថ្ងៃសៅរ៍ ៥ កើត ខែចេត្រ ឆ្នាំរោង បញ្ចស័ក ព.ស.២៥៦៧
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

# Authors and acknowledgment
Reference:
1. [Khmer New Year Time Calculation](http://www.dahlina.com/education/khmer_new_year_time.html)
2. [Google](https://www.google.com/)
3. [ChatGPT](https://chat.openai.com/auth/login)
