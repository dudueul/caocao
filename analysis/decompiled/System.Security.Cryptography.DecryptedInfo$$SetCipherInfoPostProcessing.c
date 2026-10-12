// name: System.Security.Cryptography.DecryptedInfo$$SetCipherInfoPostProcessing
// entry: 01b14508

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void System_Security_Cryptography_DecryptedInfo__SetCipherInfoPostProcessing
               (long param_1,undefined8 param_2,undefined8 param_3)

{
  undefined8 uVar1;
  long *plVar2;
  long *plVar3;
  
  if ((bRam00000000049fbd39 & 1) == 0) {
    func_0x010c65d4(0x360c);
    bRam00000000049fbd39 = 1;
  }
  plVar3 = *(long **)(param_1 + 0x10);
  if (plVar3 == (long *)0x0) {
    func_0x010eec64(0);
  }
  (**(code **)(*plVar3 + 0x1e0))(plVar3,param_3,*(undefined8 *)(*plVar3 + 0x1e8));
  plVar2 = *(long **)(param_1 + 0x10);
  plVar3 = (long *)func_0x010fdda8(_class_System_Text_ASCIIEncoding_TypeInfo);
  System_Text_ASCIIEncoding___ctor(plVar3,0);
  if (plVar3 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uVar1 = (**(code **)(*plVar3 + 0x1d0))(plVar3,param_2,*(undefined8 *)(*plVar3 + 0x1d8));
  if (plVar2 == (long *)0x0) {
    func_0x010eec64(0);
  }
  (**(code **)(*plVar2 + 0x200))(plVar2,uVar1,*(undefined8 *)(*plVar2 + 0x208));
  plVar3 = *(long **)(param_1 + 0x10);
  if (plVar3 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uVar1 = (**(code **)(*plVar3 + 0x290))(plVar3,*(undefined8 *)(*plVar3 + 0x298));
  *(undefined8 *)(param_1 + 0x18) = uVar1;
  return;
}


