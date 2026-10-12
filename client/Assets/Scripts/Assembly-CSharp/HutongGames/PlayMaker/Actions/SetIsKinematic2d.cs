using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetIsKinematic2d : ComponentAction<Rigidbody2D>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool isKinematic;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetIsKinematic()
		{
		}
	}
}
