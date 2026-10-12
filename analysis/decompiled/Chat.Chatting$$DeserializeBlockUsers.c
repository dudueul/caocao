// name: Chat.Chatting$$DeserializeBlockUsers
// entry: 01d2ff48

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__DeserializeBlockUsers(long param_1,long param_2)

{
  int iVar1;
  int iVar2;
  undefined8 uVar3;
  long lVar4;
  
  if ((bRam00000000049fcf49 & 1) == 0) {
    func_0x010c65d4(0x25bb);
    bRam00000000049fcf49 = 1;
  }
  if (param_2 != 0) {
    lVar4 = *(long *)(param_1 + 0x50);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<tk2dUICamera>__Clear
              (lVar4,_mm_Method_System_Collections_Generic_List<ChatBlockUser>_Clear__);
    iVar1 = LitJson_JsonData__get_Count(param_2,0);
    if (0 < iVar1) {
      iVar1 = 0;
      do {
        lVar4 = func_0x010fdda8(_class_Chat_ChatBlockUser_TypeInfo);
        System_Object___ctor(lVar4,0);
        uVar3 = LitJson_JsonData__get_Item(param_2,iVar1,0);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        Chat_ChatBlockUser__Deserialize(lVar4,uVar3);
        Chat_Chatting__AddBlockUser(param_1,lVar4);
        iVar1 = iVar1 + 1;
        iVar2 = LitJson_JsonData__get_Count(param_2,0);
      } while (iVar1 < iVar2);
    }
  }
  return;
}


