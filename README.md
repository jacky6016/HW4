# Open data mining - HW4 of the Course "Theory of Computation"

A program that parses open data(不動產買賣實價登錄批次資料) from Taiwanese government website (http://data.gov.tw/node/6213)

The data is about real-estate(location, address, size, price,...), which is the most popular open data from Taiwanese government.

The data is actually fetched from http://www.datagarage.io/datasets/ktchuang/realprice/A .


###Description
Given a URL which can load a Json file containing less than 2000 pieces of house trading data in our country, 
it will scan the whole data and find out "which road in a city has house trading records spread in #max_distinct_month", 
then print out the roads name and their cities with their highest sale price and lowest sale price.

