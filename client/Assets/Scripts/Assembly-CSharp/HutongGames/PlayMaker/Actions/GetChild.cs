using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetChild : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString childName;

		public FsmString withTag;

		[RequiredField]
		public FsmGameObject storeResult;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private static GameObject DoGetChildByName(GameObject root, string name, string tag)
		{
			return null;
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
