#!/usr/bin/python

import urllib2, urllib, json, sys

print ("Welcome to REST Bank!")

name = raw_input("Yor name: ")
pwd = raw_input("Password: ")

pwdAgain = raw_input("Again: ")
while pwd != pwdAgain:
	pwdAgain = raw_input("Again: ")

request = urllib2.Request("http://localhost:8080/restful-bank/account?clientName=" + name + "&clientPassword=" + pwd)
handler = urllib2.urlopen(request)
data = json.loads(handler.read())

print ("Created account " + data["accountNumber"])

def balance():
	acc = raw_input("Account number: ")
	pwd = raw_input("Password: ")
	request = urllib2.Request("http://localhost:8080/restful-bank/balance?accountNumber=" + acc + "&clientPassword=" + pwd)
	handler = urllib2.urlopen(request)
	data = json.loads(handler.read())
	print ("Balance: " + data["balance"])

def deposit():
	acc = raw_input("Account number: ")
	pwd = raw_input("Password: ")
	target = raw_input("Target account number: ")
	amount = raw_input("Amount: ")
	request = urllib2.Request("http://localhost:8080/restful-bank/deposit?accountNumber=" + acc + "&clientPassword=" + pwd + "&targetAccountNumber=" + target + "&amount=" + amount)
	handler = urllib2.urlopen(request)
	data = json.loads(handler.read())
	print ("Deposit successful")

def withdraw():
	acc = raw_input("Account number: ")
	pwd = raw_input("Password: ")
	amount = raw_input("Amount: ")
	request = urllib2.Request("http://localhost:8080/restful-bank/withdraw?accountNumber=" + acc + "&clientPassword=" + pwd + "&amount=" + amount)
	handler = urllib2.urlopen(request)
	data = json.loads(handler.read())
	print ("Withdraw successful")

def transfer():
	acc = raw_input("Account number: ")
	pwd = raw_input("Password: ")
	target = raw_input("Target account number: ")
	amount = raw_input("Amount: ")
	request = urllib2.Request("http://localhost:8080/restful-bank/transfer?fromAccountNumber=" + acc + "&clientPassword=" + pwd + "&toAccountNumber=" + target + "&amount=" + amount)
	handler = urllib2.urlopen(request)
	data = json.loads(handler.read())
	print ("Transfer successful")

def exit():
	print ("Bye bye!")
	sys.exit(0)

options = {"1" : balance,
           "2" : deposit,
           "3" : withdraw,
           "4" : transfer,
           "5" : exit,
}

while True:
	print ("1- Get Balance")
	print ("2- Deposit")
	print ("3- Withdraw")
	print ("4- Transfer")
	print ("5- Exit")
	option = raw_input("Select an option: ")
	options[option]()
