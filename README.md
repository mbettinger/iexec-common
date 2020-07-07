# iexec-common

## Steps to genreate Java Wrappers for Smart Contacts

```
git clone https://github.com/iExecBlockchainComputing/PoCo-dev/
cd PoCo-dev
git checkout x.y.z
npm i
./node_modules/.bin/truffle compile
# Get Epirus CLI (new web3j-cli)
curl -L get.epirus.io | sh
# Import latest web3j in gradle: https://github.com/web3j/web3j/releases

epirus truffle generate -t ~/iexecdev/PoCo-dev/build/contracts/App.json -o ~/iexecdev/iexec-common/src/main/java/ -p com.iexec.common.contract.generated
epirus truffle generate -t ~/iexecdev/PoCo-dev/build/contracts/Dataset.json -o ~/iexecdev/iexec-common/src/main/java/ -p com.iexec.common.contract.generated
epirus truffle generate -t ~/iexecdev/PoCo-dev/build/contracts/Ownable.json -o ~/iexecdev/iexec-common/src/main/java/ -p com.iexec.common.contract.generated
epirus truffle generate -t ~/iexecdev/PoCo-dev/build/contracts/IexecInterfaceTokenABILegacy.json -o ~/iexecdev/iexec-common/src/main/java/ -p com.iexec.common.contract.generated
#1 Rename IexecInterfaceToken.java to IexecHubContract.java
#2 Use ChainUtils.fromListString2DynamicArrayAddress & ChainUtils.fromListAddress2ListString in Task contructor when required

```
