using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetMaterial : ComponentAction<Renderer>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt materialIndex;

		[RequiredField]
		public FsmMaterial material;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetMaterial()
		{
		}
	}
}
