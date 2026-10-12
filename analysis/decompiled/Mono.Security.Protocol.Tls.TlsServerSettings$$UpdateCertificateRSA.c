// name: Mono.Security.Protocol.Tls.TlsServerSettings$$UpdateCertificateRSA
// entry: 02b05844

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Mono_Security_Protocol_Tls_TlsServerSettings__UpdateCertificateRSA(long param_1)

{
  int iVar1;
  undefined4 uVar2;
  long *plVar3;
  long *plVar4;
  long lVar5;
  undefined8 uStack_b0;
  undefined8 uStack_a8;
  undefined8 uStack_a0;
  undefined8 uStack_98;
  undefined8 uStack_90;
  undefined8 uStack_88;
  undefined8 uStack_80;
  undefined8 uStack_78;
  undefined8 uStack_70;
  undefined8 uStack_68;
  undefined8 uStack_60;
  undefined8 uStack_58;
  undefined8 uStack_50;
  undefined8 uStack_48;
  undefined8 uStack_40;
  undefined8 uStack_38;
  
  if ((bRam0000000004a04dc4 & 1) == 0) {
    func_0x010c65d4(0xd23d);
    bRam0000000004a04dc4 = 1;
  }
  if ((*(long *)(param_1 + 0x10) == 0) ||
     (iVar1 = System_Collections_CollectionBase__get_Count(*(long *)(param_1 + 0x10),0), iVar1 == 0)
     ) {
    *(undefined8 *)(param_1 + 0x18) = 0;
  }
  else {
    lVar5 = *(long *)(param_1 + 0x10);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    plVar3 = (long *)Mono_Security_X509_X509CertificateCollection__get_Item(lVar5,0,0);
    if (plVar3 == (long *)0x0) {
      func_0x010eec64(0);
    }
    plVar3 = (long *)(**(code **)(*plVar3 + 0x1d0))(plVar3,*(undefined8 *)(*plVar3 + 0x1d8));
    if (plVar3 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uVar2 = (**(code **)(*plVar3 + 400))(plVar3,*(undefined8 *)(*plVar3 + 0x198));
    plVar3 = (long *)func_0x010fdda8(_class_Mono_Security_Cryptography_RSAManaged_TypeInfo);
    Mono_Security_Cryptography_RSAManaged___ctor(plVar3,uVar2,0);
    lVar5 = *(long *)(param_1 + 0x10);
    *(long **)(param_1 + 0x18) = plVar3;
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    plVar4 = (long *)Mono_Security_X509_X509CertificateCollection__get_Item(lVar5,0,0);
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
    }
    plVar4 = (long *)(**(code **)(*plVar4 + 0x1d0))(plVar4,*(undefined8 *)(*plVar4 + 0x1d8));
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
    }
    (**(code **)(*plVar4 + 0x220))(&uStack_b0,plVar4,0,*(undefined8 *)(*plVar4 + 0x228));
    if (plVar3 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uStack_48 = uStack_88;
    uStack_50 = uStack_90;
    uStack_38 = uStack_78;
    uStack_40 = uStack_80;
    uStack_68 = uStack_a8;
    uStack_70 = uStack_b0;
    uStack_58 = uStack_98;
    uStack_60 = uStack_a0;
    (**(code **)(*plVar3 + 0x230))(plVar3,&uStack_70,*(undefined8 *)(*plVar3 + 0x238));
  }
  return;
}


