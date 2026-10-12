// name: HttpRequest$$Send
// entry: 016dd42c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void HttpRequest__Send(long param_1)

{
  byte bVar1;
  uint uVar2;
  undefined8 uVar3;
  long lVar4;
  
  uVar3 = HttpRequest__Construct();
  lVar4 = *(long *)(param_1 + 0x30);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam0000000004a0137d & 1) == 0) {
    func_0x010c65d4(0x6b97,uVar3,0);
    bRam0000000004a0137d = 1;
  }
  uVar2 = System_Collections_Generic_Queue<string>__Contains
                    (lVar4,uVar3,
                     _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                    );
  if ((uVar2 & 1) == 0) {
    System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
              (lVar4,uVar3,
               _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
              );
    bVar1 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar4);
    *(byte *)(lVar4 + 0x28) = bVar1 & 1;
  }
  return;
}


