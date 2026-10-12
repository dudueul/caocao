// name: Chat.Chatting$$ToObjectForGenericReflection<object>
// entry: 026d4958

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__ToObjectForGenericReflection<object>
               (undefined8 param_1,undefined8 param_2,long param_3)

{
  if ((bRam0000000004a01f60 & 1) == 0) {
    func_0x010c65d4(0x25f6);
    bRam0000000004a01f60 = 1;
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
                    /* WARNING: Could not recover jumptable at 0x026d49d4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)**(undefined8 **)(param_3 + 0x30))(0,param_2);
  return;
}


