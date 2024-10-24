# Blockchain in Java

Implementation of Blockchain in Java.

## Description

This project is going to implement Blockchain from zero to io infinity and beyond.

### Dependencies

* Java 17
* Maven (to compile with)

## Version History
* Version 4
  * This implementation includes peer-to-peer networking with WebSockets, Proof of Stake (PoS) consensus with slashing, and blockchain data structures for block and transaction management.
  * Summary of features:
    * Block Structure: Each block holds transactions, a hash of the previous block, and is mined using a Proof of Work mechanism. 
    * Blockchain Class: Manages adding new blocks, validators, and chain integrity. 
    * Validator Class: Implements Proof of Stake (PoS) with slashing for malicious behavior. 
    * P2P Networking: WebSocket-based real-time communication between nodes, broadcasting new blocks and transactions. 
    * Hashing Utility: Implements SHA-256 hashing for creating secure block hashes.
* Version 3
  * Expand the previous blockchain implementation by adding peer-to-peer networking, rewarding miners, and implementing Proof of Stake (PoS) requires several modifications.
* Version 2
  * Expand the basic implementation with key blockchain features such as digital signatures for transactions, distributed consensus, and handling multiple nodes.
* Version 1
  * This is a basic implementation created blocks, a chain of blocks, and implemented the Proof of Work algorithm

## License

This project is licensed under the [MIT] License