// name: Chat.Chatting$$OnCheckBanContent
// entry: 01d2e6d8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnCheckBanContent(void)

{
  undefined8 uVar1;
  undefined8 uVar2;
  byte bVar3;
  uint uVar4;
  long lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  
  if ((bRam00000000049fcf2e & 1) == 0) {
    func_0x010c65d4(0x25e4);
    bRam00000000049fcf2e = 1;
  }
  if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<HttpRequest>_get_instance__)
  ;
  if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Chat_Chatting_TypeInfo);
  }
  uVar2 = _str__bancontents_getContentsList;
  uVar1 = _str_POST;
  uVar7 = _mm_Method_Chat_Chatting_<OnCheckBanContent>m__0__;
  if (*(long *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 8) == 0) {
    uVar6 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
    HttpRequest_ResponseCallback___ctor(uVar6,0,uVar7,0);
    if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 8) = uVar6;
  }
  if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Chat_Chatting_TypeInfo);
  }
  uVar6 = *(undefined8 *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 8);
  uVar7 = func_0x01101ad4(_class_object___TypeInfo,0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = HttpRequest__Construct(lVar5,uVar2,uVar1,0,uVar6,0,0,uVar7);
  lVar5 = *(long *)(lVar5 + 0x30);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam0000000004a0137d & 1) == 0) {
    func_0x010c65d4(0x6b97,uVar7,0);
    bRam0000000004a0137d = 1;
  }
  uVar4 = System_Collections_Generic_Queue<string>__Contains
                    (lVar5,uVar7,
                     _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                    );
  if ((uVar4 & 1) == 0) {
    System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
              (lVar5,uVar7,
               _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
              );
    bVar3 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar5);
    *(byte *)(lVar5 + 0x28) = bVar3 & 1;
  }
  return;
}


