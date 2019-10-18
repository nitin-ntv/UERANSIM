import { create } from 'zustand'

export const [useLogStore] = create<LogStore>(set => ({
    logs: [],
    clearLogs: () => set(s => ({ ...s, logs: [] })),
    getTaggedLogger: (tag: string) => {
        const createFn = (entry: string, type: LogType) => set(s => ({ ...s, logs: [...s.logs, { entry: `[${tag}] ${entry}`, type }] }));
        return {
            debug: (entry: string) => createFn(entry, LogType.DEBUG),
            info: (entry: string) => createFn(entry, LogType.INFO),
            error: (entry: string) => createFn(entry, LogType.ERROR),
            warning: (entry: string) => createFn(entry, LogType.WARNING)
        };
    }
}))

type LogStore = {
    logs: Log[];
    getTaggedLogger: (tag: string) => Logger
}

type Log = {
    entry: string;
    type: LogType;
}
type Logger = {
    debug: (entry: string) => void;
    warning: (entry: string) => void;
    error: (entry: string) => void;
    info: (entry: string) => void;
}


export enum LogType {
    "WARNING",
    "DEBUG",
    "ERROR",
    "INFO"
}
