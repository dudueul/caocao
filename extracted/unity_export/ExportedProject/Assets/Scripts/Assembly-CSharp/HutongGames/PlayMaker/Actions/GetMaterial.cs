using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetMaterial : ComponentAction<Renderer>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt materialIndex;

		[RequiredField]
		public FsmMaterial material;

		public bool getSharedMaterial;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetMaterial()
		{
		}
	}
}
