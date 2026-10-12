// name: FallbackServerSetting$$.ctor
// entry: 0174ae0c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FallbackServerSetting___ctor(long param_1,long param_2)

{
  byte bVar1;
  uint uVar2;
  undefined8 uVar3;
  
  if ((bRam00000000049f9c21 & 1) == 0) {
    func_0x010c65d4(0x5688);
    bRam00000000049f9c21 = 1;
  }
  System_Object___ctor(param_1,0);
  *(undefined1 *)(param_1 + 0x48) = 0;
  if (param_2 == 0) {
    return;
  }
  uVar3 = *(undefined8 *)(param_1 + 0x10);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = Util__GetData(0,param_2,_str_NAME,uVar3,0);
  *(undefined8 *)(param_1 + 0x10) = uVar3;
  uVar3 = Util__GetData(0,param_2,_str_GAME_SERVER_HOST_URL,*(undefined8 *)(param_1 + 0x18),0);
  *(undefined8 *)(param_1 + 0x18) = uVar3;
  uVar3 = Util__GetData(0,param_2,_str_CDN_URL,*(undefined8 *)(param_1 + 0x20),0);
  *(undefined8 *)(param_1 + 0x20) = uVar3;
  bVar1 = Util__GetData(0,param_2,_str_USE_PACKET_ENCRYPT,*(undefined1 *)(param_1 + 0x28),0);
  *(byte *)(param_1 + 0x28) = bVar1 & 1;
  uVar3 = Util__GetData(0,param_2,_str_NPA_SERVICE_ID,*(undefined8 *)(param_1 + 0x30),0);
  *(undefined8 *)(param_1 + 0x30) = uVar3;
  uVar3 = Util__GetData(0,param_2,_str_GOOGLE_PUSH_SENDER_ID,*(undefined8 *)(param_1 + 0x38),0);
  *(undefined8 *)(param_1 + 0x38) = uVar3;
  uVar3 = Util__GetData(0,param_2,_str_S3_QA_URL,*(undefined8 *)(param_1 + 0x40),0);
  *(undefined8 *)(param_1 + 0x40) = uVar3;
  uVar3 = *(undefined8 *)(param_1 + 0x18);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = System_String__IsNullOrEmpty(0,uVar3,0);
  if ((uVar2 & 1) == 0) {
    uVar3 = *(undefined8 *)(param_1 + 0x20);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = System_String__IsNullOrEmpty(0,uVar3,0);
    if ((uVar2 & 1) == 0) {
      uVar3 = *(undefined8 *)(param_1 + 0x30);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar2 = System_String__IsNullOrEmpty(0,uVar3,0);
      if ((uVar2 & 1) == 0) {
        uVar3 = *(undefined8 *)(param_1 + 0x40);
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        bVar1 = System_String__IsNullOrEmpty(0,uVar3,0);
        bVar1 = ~bVar1 & 1;
        goto code_r0x0174b028;
      }
    }
  }
  bVar1 = 0;
code_r0x0174b028:
  if (param_1 == 0) {
    func_0x010eec64(0);
  }
  *(byte *)(param_1 + 0x48) = bVar1;
  return;
}


