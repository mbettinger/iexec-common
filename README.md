# iexec-common

## Steps to generate Java Wrappers for Smart Contacts

```
git clone https://github.com/iExecBlockchainComputing/PoCo-dev/
cd PoCo-dev
git checkout x.y.z
npm i
./node_modules/.bin/truffle compile
# Get web3j-cli
curl -L https://get.epirus.io | sh
# Import latest web3j in gradle: https://github.com/web3j/web3j/releases
# Extract ABI with https://www.npmjs.com/package/truffle-abi

# Generate Java wrapper
epirus generate solidity generate --abiFile=$HOME/iexecdev/PoCo-dev/build/abi/DatasetRegistry.json -o ~/iexecdev/iexec-common/src/main/java/ -p com.iexec.common.contract.generated
# Then (1) Rename IexecInterfaceToken.java to IexecHubContract.java
# And (2)  Use ChainUtils.fromList2Address() if necessary

# Same applies for App, Dataset, Ownable

```

## Progress on feature/abi-v2

```
2020-11-30 15:31:21.713  INFO 1 --- [    Scheduled-4] com.iexec.core.chain.DealWatcherService  : Received deal [dealId:0x65f7ea228630acaf18dabbd87776c28d4a084d5e60801168943c226731654217, block:312]
2020-11-30 15:31:21.722 ERROR 1 --- [    Scheduled-4] c.i.c.chain.IexecHubAbstractService      : Failed to get ChainDeal [chainDealId:0x65f7ea228630acaf18dabbd87776c28d4a084d5e60801168943c226731654217]

java.lang.StringIndexOutOfBoundsException: String index out of range: -1024
	at java.base/java.lang.String.substring(Unknown Source)
	at org.web3j.abi.TypeDecoder.decodeDynamicStructElements(TypeDecoder.java:510)
	at org.web3j.abi.TypeDecoder.decodeDynamicStruct(TypeDecoder.java:464)
	at org.web3j.abi.DefaultFunctionReturnDecoder.build(DefaultFunctionReturnDecoder.java:95)
	at org.web3j.abi.DefaultFunctionReturnDecoder.decodeFunctionResult(DefaultFunctionReturnDecoder.java:49)
	at org.web3j.abi.FunctionReturnDecoder.decode(FunctionReturnDecoder.java:49)
	at org.web3j.tx.Contract.executeCall(Contract.java:294)
	at org.web3j.tx.Contract.executeCallSingleValueReturn(Contract.java:300)
	at org.web3j.tx.Contract.executeCallSingleValueReturn(Contract.java:311)
	at org.web3j.tx.Contract.lambda$executeRemoteCallSingleValueReturn$1(Contract.java:399)
	at org.web3j.protocol.core.RemoteCall.send(RemoteCall.java:42)
	at com.iexec.common.chain.IexecHubAbstractService.getChainDeal(IexecHubAbstractService.java:180)

Track issue here: https://github.com/web3j/web3j/issues/1278
```
