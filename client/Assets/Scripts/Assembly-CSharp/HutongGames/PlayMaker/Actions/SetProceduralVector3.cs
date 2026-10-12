namespace HutongGames.PlayMaker.Actions
{
	public class SetProceduralVector3 : FsmStateAction
	{
		[RequiredField]
		public FsmMaterial substanceMaterial;

		[RequiredField]
		public FsmString vector3Property;

		[RequiredField]
		public FsmVector3 vector3Value;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetProceduralVector()
		{
		}
	}
}
