# Min's Simple Stock Exchange System

## Running the program

<i> Requirements:
    Java JDK (https://www.oracle.com/java/technologies/downloads/#jdk17-windows)<br>
    Environment variable set up to use java in terminal (https://www.ibm.com/docs/en/b2b-integrator/5.2?topic=installation-setting-java-variables-in-windows)
    </i>
<br>
1. Open Command Prompt or your computer's Terminal console (<b>Win+R → cmd</b> in Windows)
2. Navigate to directory that contains the <b>stockexchangesystem.jar </b><br>
![Directory in cmd](Pictures/picture1.png "Directory in cmd")

3. Enter command "java -jar stockexchangesystem.jar"

**Alternatively,**

1. From the terminal, navigate to ../src folder inside the directory

2. Enter command "javac MainApp.java" to compile the MainApp
3. Enter command "java MainApp.java" to run the program

## Introduction

This program is built using Java to simulate a simple stock exchange system, where the user can request market or limit buy/sell orders via the console.

The system maintains three main <b>"databases": The history of all orders made, the current pending buy/sell orders, and the last traded price of a stock.</b>

The system is based on the assumption that **the highest priced buy order and the lowest priced sell order are given priority**. This means that when we make the market order, we are looking for the **best price available.** Likewise, when we make a limit order, we are looking for the best price available, but with **a limit to stop the trade from executing if the available price is beyond our limit.**

## System Overview

The **MainApp** application interacts with **MenuManager** to print various menus to display into the terminal. User input is received via the **LogicUnit** classes, which resolve what actions the system should perform based on the user's inputs.

When the user wants to create an order, the **TradeLogicUnit** runs and executes it's logic, where it asks the user various information about the order to create (Stock name, limit/market order type, buy/sell, quantity, etc.) Once an **Order** object is successfully created, it is then received by the **OrderManager** class via its **receiveOrder()** function.

Inside **receiveOrder()**, the OrderManager will attempt to find a matching order from its list of pending buy/sell orders. It will then execute the trade between the user's order and matching order via the **executeTrade()** function of the **Order** class.

Inside **executeTrade()**, it will be checked if the matching order is real or null; if it is null, or if the user's order can only be partially fulfilled, relevant values will be updated (quantity fulfilled, order status) and **the order will be added to OrderManager's corresponding pending buy/sell list**. If the user's order can be completely fulfilled, relevant values will be updated and **the order will not be added to the pending buy/sell lists.** At the end of the function, the **StockManager** will be updated with the latest traded price of the stock if there was a successful trade.

## Test Cases

The system is able to handle simple scenarios as well as more complex interactions when orders of different types are interleaved. Some pointers for resolving trades and getting quotes:<br>

- When executing trades for a user order, **all matching orders in the pending list must be executed while the user order is not yet completely fulfilled.** Failure to do so will result in only the first matching order being completed.

- If there is a pending market order and limit order of the same type (buy/sell), **the market order is prioritised first**. This is due to how the pending lists are maintained, by sorting based on the order's prices. A market order has maximum/minimum value buy and sell prices respectively, which gives them the highest priority when sorting the pending list.

- Since a market order uses the maximum/minimum value buy/sell prices, when finding a quote for a stock, if there are **only pending market buy/sell orders**, we **should not display the ask and bid prices** as they should not be available.

### Limit - Limit scenario
This is the simplest scenario, where we only have limit buy and limit sell orders.

### Limit - Market scenario

## Design Principles
