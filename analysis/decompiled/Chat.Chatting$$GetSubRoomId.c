// name: Chat.Chatting$$GetSubRoomId
// entry: 01d2afb0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long Chat_Chatting__GetSubRoomId(undefined8 param_1,long param_2)

{
  int iVar1;
  long lVar2;
  int iVar3;
  long lVar4;
  undefined8 uVar5;
  
  if ((bRam00000000049fcf35 & 1) == 0) {
    func_0x010c65d4(0x25d2);
    bRam00000000049fcf35 = 1;
  }
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar2 = _str__sub_;
  if ((bRam0000000004a03ad9 & 1) == 0) {
    func_0x010c65d4(0xcce8,param_2,_str__sub_,0);
    bRam0000000004a03ad9 = 1;
  }
  if ((param_2 == 0) || (iVar3 = *(int *)(param_2 + 0x10), iVar3 == 0)) {
    if ((lVar2 == 0) || (param_2 = lVar2, *(int *)(lVar2 + 0x10) == 0)) {
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      param_2 = **(long **)(_class_string_TypeInfo + 0xb8);
    }
  }
  else if ((lVar2 != 0) && (iVar1 = *(int *)(lVar2 + 0x10), iVar1 != 0)) {
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar4 = func_0x01103084(iVar1 + iVar3);
    iVar3 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
    uVar5 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
    System_String__CharCopy
              (uVar5,lVar4 + iVar3,param_2 + (int)uVar5,*(undefined4 *)(param_2 + 0x10));
    iVar3 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
    uVar5 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
    System_String__CharCopy
              (uVar5,lVar4 + iVar3 + (long)(*(int *)(param_2 + 0x10) << 1),lVar2 + (int)uVar5,
               *(undefined4 *)(lVar2 + 0x10));
    param_2 = lVar4;
  }
  return param_2;
}


