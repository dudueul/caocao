using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetRandomMaterial : ComponentAction<Renderer>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt materialIndex;

		public FsmMaterial[] materials;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetRandomMaterial()
		{
		}
	}
}
