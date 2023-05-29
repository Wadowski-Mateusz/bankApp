import React, { useState } from "react";

interface Note {
  id: number;
  content: string;
}

export default function MyComponent() {
  const [notes, setNotes] = useState<Note[]>([]);
  const [showForm, setShowForm] = useState(false);
  const [noteInput, setNoteInput] = useState("");

  const handleAddNote = () => {
    setShowForm(true);
  };

  const handleNoteInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNoteInput(e.target.value);
  };

  const handleSaveNote = () => {
    const newNote: Note = {
      id: Date.now(),
      content: noteInput,
    };

    setNotes([...notes, newNote]);
    setShowForm(false);
    setNoteInput("");
  };

  return (
    <div>
      <button onClick={handleAddNote}>Add Note</button>

      {showForm && (
        <>
            <input
              type="text"
              value={noteInput}
              onChange={handleNoteInputChange} />
            <button onClick={handleSaveNote}>Save</button>
          </>
      )}

      <div>
        <h2>Notes</h2>
        {notes.map((note) => (
          <div key={note.id}>{note.content}</div>
        ))}
      </div>
    </div>
  );
}
