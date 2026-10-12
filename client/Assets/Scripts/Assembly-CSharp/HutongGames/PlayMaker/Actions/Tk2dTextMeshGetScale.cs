using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshGetScale : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmVector3 scale;

		public bool everyframe;

		private GameObject go;

		private tk2dTextMesh _textMesh;

		private void _getTextMesh()
		{
		}

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnExit()
		{
		}

		private void DoGetScale()
		{
		}
	}
}
