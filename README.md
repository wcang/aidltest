# aidltest
Code to test AIDL. Demo on 2 way method calls. Client register callback. Server calls callback in client's process context

## TODO
- Add more threads to invoke server's method and see if there is indeed a threadpool of 16 threads.
- Try to induce race condition.
