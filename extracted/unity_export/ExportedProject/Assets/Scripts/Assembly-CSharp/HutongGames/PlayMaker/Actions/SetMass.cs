using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetMass : ComponentAction<Rigidbody>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat mass;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetMass()
		{
		}
	}
}
