using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetMass : ComponentAction<Rigidbody>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat storeResult;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetMass()
		{
		}
	}
}
