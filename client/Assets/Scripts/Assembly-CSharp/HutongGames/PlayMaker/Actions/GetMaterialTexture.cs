using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetMaterialTexture : ComponentAction<Renderer>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt materialIndex;

		public FsmString namedTexture;

		[RequiredField]
		public FsmTexture storedTexture;

		public bool getFromSharedMaterial;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetMaterialTexture()
		{
		}
	}
}
