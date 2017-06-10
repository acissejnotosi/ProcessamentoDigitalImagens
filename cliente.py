#!/usr/bin/python
##Client RestFul in python
##Author: Allan Patrick
##Distribuidos
## run command line use $ python3 client.py


import urllib.request, json, sys,requests,ast, random


print ("Welcome to REST Bank!")

# Cliente brief entry witch the client have the option to entry with a account already existence
teste ="false"
while  teste == "false":
    print("1-New Account:")
    print("2-Aready user:")
    menu = input()
    if menu=="1":
        myaccount = random.randint(1000,9999)
        print("MyAccount:",myaccount)
        myagency = random.randint(500,999)
        print("MyAgency:",myagency)
        data = {"id":1,"clientName":1,"clientPassword":1, "bank":1,"type":1,"numberAccount":myaccount,"agency":myagency, "balance":100.0}
        myurl = "http://localhost:8080/account/new"
        headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
        r = requests.post(myurl, data=json.dumps(data), headers=headers)
        token = r.json()["token"]
        pwd = input("New password: ")
        #print(token)
        myKeyJson = r.json()
        myKeyTex =ast.literal_eval(r.text)
        #print(myKeyTex)
        teste="true"
    else:
        acc = input("Account number: ")
        agency = input("Agency account number: ")
        pwd = input("pwd: ")
        bank = input("Bank: ")
        type = input("Type: ")
        data = {"id": 1, "clientName": 1, "clientPassword": pwd, "bank":bank , "type": type, "numberAccount": acc,"agency": agency}
        myurl = "http://localhost:8080/account/verify"
        headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
        r = requests.post(myurl, data=json.dumps(data), headers=headers)
        print(r.text)
        if r.text!="false":
            teste="true"
            token = r.json()["token"]
            #print(token)
            myKeyJson = r.json()
            myKeyTex = ast.literal_eval(r.text)
            #print(myKeyTex)
        else:
            print("Client doesn't exist try again")


pwdconfirm = input("confirm: ")
while pwd != pwdconfirm:
    pwdconfirmed = input("confirm: ")



# Calls the method get balance in the server for this client
# Send a json with token
def balance():
    myurl = "http://localhost:8080/account/balance"
    headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
    r = requests.post(myurl, data=json.dumps(myKeyTex), headers=headers)
    print ("Balance: ",r.text)


# Calls the method deposit somewhere  account informed by account number , agency and type
# Send a json with token or dates of an account
def deposit():
    acc =input("Account number: ")
    agency =input("Agency account number: ")
    amount =input("Amount: ")
    data = {"numberAccount":acc,"agency":agency,"value":amount}
    myurl = "http://localhost:8080/account/deposit"
    headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
    r = requests.post(myurl, data=json.dumps(data), headers=headers)
    teste =  r.text
    if teste=="true":
        print("Deposit successful")
    else:
        print("Deposit faild")

# Calls the method withdraw into the server from this client account
# Send a json with token
def withdraw():
    myurl = "http://localhost:8080/account/withdraw"
    amount = input("Amount: ")
    data = {"token":token,"value": amount}
    headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
    r = requests.post(myurl, data=json.dumps(data), headers=headers)
    teste = r.text
    if teste == "true":
        print("Withdraw successful")
    else:
        print("Withdraw faild")
# Calls the method  transfer into the server from this client account  to another specified in the json
# Send a json with token and data from the account witch wish transfer
def transfer():
    bank= input("Number of bank:")
    type = input("Type of Account:")
    acc = input("Account number: ")
    agency = input("Agency account number: ")
    amount = input("Amount: ")
    data = {"numberAccount": acc, "agency": agency, "value": amount,"token":token,"bank":bank,"type":type}
    myurl = "http://localhost:8080/account/transfer"
    headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
    r = requests.post(myurl, data=json.dumps(data), headers=headers)
    teste = r.text
    print (r.text)
    if teste == "true":
        print("Transfer successful")
    else:
        print("Transfer faild")


def exit():
    print ("Bye bye!")
    sys.exit(0)

options = {"1" : balance,
           "2" : deposit,
           "3" : withdraw,
           "4" : transfer,
           "5" : exit,
           }
#the mamu options
while True:
    print ("1- Get Balance")
    print ("2- Deposit")
    print ("3- Withdraw")
    print ("4- Transfer")
    print ("5- Exit")
    option =input("Select an option: ")
    options[option]()


