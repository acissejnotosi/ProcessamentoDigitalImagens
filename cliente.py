#!/usr/bin/python
#from urllib.request import urlopen
import urllib.request, json, sys,requests
#from tkinter import *
#import urllib, urllib, json, sys

print ("Welcome to REST Bank!")

data = {"id":1,"clientName":"Client","clientPassword":"password"}

myurl = "http://localhost:8080/account/new"
headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
r = requests.post(myurl, data=json.dumps(data), headers=headers)
print ("Mykey: "+r.json()["token"])
myKeyJson = r.json()
myKeyTex = r.text
print (myKeyTex)
print



pwd = input("pwd: ")

pwdAgain = input("Again: ")
while pwd != pwdAgain:
 caspwdAgain =input("Again: ")




def balance():
    myurl = "http://localhost:8080/account/balance"
    headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
    r = requests.post(myurl, data= r.json(), headers=headers)
    print ("Balance: ",r.text)


def deposit():
	acc =input("Account number: ")
	pwd =input("Password: ")
	target =input("Target account number: ")
	amount =input("Amount: ")
	request = urllib.Request("http://localhost:8080/restful-bank/deposit?accountNumber=" + acc + "&clientPassword=" + pwd + "&targetAccountNumber=" + target + "&amount=" + amount)
	handler = urllib.urlopen(request)
	data = json.loads(handler.read())
	print ("Deposit successful")

def withdraw():
	acc =input("Account number: ")
	pwd =input("Password: ")
	amount =input("Amount: ")
	request = urllib.Request("http://localhost:8080/restful-bank/withdraw?accountNumber=" + acc + "&clientPassword=" + pwd + "&amount=" + amount)
	handler = urllib.urlopen(request)
	data = json.loads(handler.read())
	print ("Withdraw successful")

def transfer():
	acc =input("Account number: ")
	pwd =input("Password: ")
	target =input("Target account number: ")
	amount =input("Amount: ")
	request = urllib.Request("http://localhost:8080/restful-bank/transfer?fromAccountNumber=" + acc + "&clientPassword=" + pwd + "&toAccountNumber=" + target + "&amount=" + amount)
	handler = urllib.urlopen(request)
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
	option =input("Select an option: ")
	options[option]()
